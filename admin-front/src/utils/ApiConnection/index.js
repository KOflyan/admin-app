import axios from "axios";

axios.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    config.headers.Authorization = token;
    return config;
})

const ApiConnection = {

  all: function(url, skip, limit) { return getAll(url, skip, limit) },
  get: function(url, id) { return getById(url, id) },
  save: function(url, data, cb) { return save(url, data, cb) },
  delete: function(url, id) { deleteById(url, id) },
  registerAdmin: function(data, cb) { registerAdmin(data, cb) },
  register: function(data, url, cb) { register(data, url, cb) },
  countByLanguage: function(cb) { countByLanguage(cb) },
  countByDeviceType: function(cb) { countByDeviceType(cb) },
  countByAccountType: function(cb) { countByAccountType(cb) },
  countRecent: function(interval, cb) { countRecent(interval, cb) }

}

function getAll(url, skip, limit, cb) {
  const promiseAll = get(url, skip, limit);
  const promiseCount = count(url);

  return Promise.all([promiseAll, promiseCount]);
}

function get(url, skip, limit) {
  return axios.get('/' + url + '/all?skip=' + skip + '&limit=' + limit)
}

function count(url) {
  return axios.get('/' + url + '/count')
}

function getById(url, id) {
  return axios.get('/' + url + '/' + id)
}

function save(url, data, cb) {
  data['type'] = url;
  return axios.post('/' + url + '/save', data)
}

function deleteById(url, id) {
  axios.post('/' + url + '/delete/' + id)
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

  .then( response => response.json() )
  .then(cb);
}

function countByDeviceType(cb) {
  return fetch('/device/countByFamily')

  .then( response => response.json() )
  .then(cb);
}

function countByAccountType(cb) {
  return fetch('/account/countByType')

  .then( response => response.json() )
  .then(cb);
}

function countRecent(interval, cb) {
  return fetch('/user/countRecent/' + interval)

  .then( response => response.json() )
  .then(cb);
}

export default ApiConnection;
