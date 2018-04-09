const ApiConnection = {

  all: function(url, cb) { getAll(url, cb) },
  get: function(url, id, cb) { getById(url, id, cb) },
  update: function(url, data) { updateInfo(url, data) }

}

function getAll(url, cb) {
  return fetch(url + '/all')
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function getById(url, id, cb) {
  return fetch(url + '/' + id)
  .then( response => checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function updateInfo(url, data) {
  console.log(data);
  fetch(url + '/update', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      data: data
    })
  })
}


function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(`HTTP Error ${response.statusText}`);
  error.status = response.statusText;
  error.response = response;
  console.log(error);
  throw error;
}

export default ApiConnection;
