import ApiConnection from './../ApiConnection';

const UserApi = {

  all: function(cb) { getAllUsers(cb) },
  get: function(id, cb) { getUserById(id, cb) }

}

function getAllUsers(cb) {
  return fetch('/user/all')
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then(response => {
    return response.json();
  }).then(cb);
}

function getUserById(id, cb) {
  return fetch('/user/' + id)
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then( response => response.json() )
  .then(cb);
}


export default UserApi;
