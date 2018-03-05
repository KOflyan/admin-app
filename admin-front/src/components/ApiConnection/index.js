function getAllUsers(cb) {
  return fetch(`/user/all`)
  .then(response => {
    return checkStatus(response);
  }).then(response => {
    return response.json();
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
  throw error;
}

const ApiConnection = { getAllUsers };

export default ApiConnection;
