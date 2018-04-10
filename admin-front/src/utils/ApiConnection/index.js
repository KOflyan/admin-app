const ApiConnection = {

  all: function(url, cb) { getAll(url, cb) },
  get: function(url, id, cb) { getById(url, id, cb) },
  update: function(url, data) { updateInfo(url, data) },
  delete: function(url, id) { deleteById(url, id) },
  registerAdmin: function(data, cb) { registerAdmin(data, cb) },
  register: function(data, url, cb) { register(data, url, cb) }

}

function getAll(url, cb) {
  return fetch('/' + url + '/all')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function getById(url, id, cb) {
  return fetch('/' + url + '/' + id)
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function updateInfo(url, data) {
  data['type'] = url;
  fetch('/' + url + '/save', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  })
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
