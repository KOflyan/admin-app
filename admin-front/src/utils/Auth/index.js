import axios from "axios";

export function isLoggedIn() {
  if (localStorage.getItem("token")) {
    return true;
  }
  return false;
}

export function logout() {
  axios.get('/logout');
  localStorage.removeItem("token");
}

export function isAdmin() {
  axios.get('/getAuthority')
  .then(response => {
    if (response.data[0].authority === 'admin') return true;
    return false;
  })
  .catch(error => console.log(error))
}
