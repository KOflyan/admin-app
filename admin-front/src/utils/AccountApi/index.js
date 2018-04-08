import ApiConnection from './../ApiConnection';

const AccountApi = {

  all: function(cb) { getAllAccounts(cb) },
  get: function(id, cb) { getAccountById(id, cb) },
  update: function(data) { updateAccountInfo(data) }

}

function getAllAccounts(cb) {
  return fetch('/account/all')
  .then(response => ApiConnection.checkStatus(response) )
  .then( response => response.json() )
  .then(cb);
}

function getAccountById(id, cb) {
  return fetch('/account/' + id)
  .then(response => {
    return ApiConnection.checkStatus(response);
  }).then( response => response.json() )
  .then(cb);
}

function updateAccountInfo(data) {
  console.log(data);
  fetch('/account/update', {
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


export default AccountApi;
