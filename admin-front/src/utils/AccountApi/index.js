import ApiConnection from './../ApiConnection';

const AccountApi = {

  all: function(cb) { getAllAccounts(cb) },
  get: function(id, cb) { getAccountById(id, cb) }

}

function getAllAccounts(cb) {
  return fetch('/account/all')
  .then(response => ApiConnection.checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function getUserById(id, cb) {
  return fetch('/account/' + id)
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then( response => response.json() )
  .then(cb);
}


export default UserApi;
