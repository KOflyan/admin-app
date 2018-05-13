import axios from "axios";

export function isLoggedIn() {
  if (localStorage.getItem("token")) {
    return true;
  }
  return false;
}

export function logout() {
  // axios.post('/logout');
  localStorage.removeItem("token");
}
