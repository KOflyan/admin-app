const ApiConnection = {

  all: function(url, skip, limit, cb) { getAll(url, skip, limit, cb) },
  get: function(url, id, cb) { getById(url, id, cb) },
  save: function(url, data, cb) { save(url, data, cb) },
  delete: function(url, id) { deleteById(url, id) },
  registerAdmin: function(data, cb) { registerAdmin(data, cb) },
  register: function(data, url, cb) { register(data, url, cb) },
  countByLanguage: function(cb) { countByLanguage(cb) },
  countByDeviceType: function(cb) { countByDeviceType(cb) },
  countByAccountType: function(cb) { countByAccountType(cb) }

}

function getAll(url, skip, limit, cb) {
  const promiseAll = get(url, skip, limit);
  const promiseCount = count(url);

  Promise.all([promiseAll, promiseCount])
  .then(data => {
    cb({
      data: data[0],
      count: data[1]
    })
  })

}

function get(url, skip, limit) {
  return fetch('/' + url + '/all?skip=' + skip + '&limit=' + limit)
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(res => {
    return res;
  });
}

function count(url) {
  return fetch('/' + url + '/count')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(res => {
    return res;
  });
}

function getById(url, id, cb) {
  return fetch('/' + url + '/' + id)
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function save(url, data, cb) {
  data['type'] = url;
  fetch('/' + url + '/save', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  }).then(cb);
}

function deleteById(url, id) {
  fetch('/' + url + '/delete/' + id, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    }
  })
}

function registerAdmin(data, cb) {
  fetch('/admin/save', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  }).then(cb);
}

function register(data, url, cb) {
  fetch('/admin/save', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  }).then(cb);
}

function countByLanguage(cb) {
  return fetch('/user/countByLanguage')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function countByDeviceType(cb) {
  return fetch('/device/countByFamily')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function countByAccountType(cb) {
  return fetch('/account/countByType')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}


function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  console.log(error);
  return error;
}

export default ApiConnection;
