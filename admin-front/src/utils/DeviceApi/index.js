import ApiConnection from './../ApiConnection';

const DeviceApi = {

  all: function(cb) { getAllDevices(cb) },
  get: function(id, cb) { getDeviceById(id, cb) },
  update: function(data) { updateDeviceInfo(data) }

}

function getAllDevices(cb) {
  return fetch('/device/all')
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then(response => {
    return response.json();
  }).then(cb);
}

function getDeviceById(id, cb) {
  return fetch('/device/' + id)
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then( response => response.json() )
  .then(cb);
}

function updateDeviceInfo(data) {
  console.log(data);
  fetch('/device/update', {
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


export default DeviceApi;
