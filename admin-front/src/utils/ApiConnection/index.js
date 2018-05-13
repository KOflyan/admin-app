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
  registerAdmin: function(data) { return registerAdmin(data) },
  countByLanguage: function() { return countByLanguage() },
  countByDeviceType: function() { return countByDeviceType() },
  countByAccountType: function() { return countByAccountType() },
  countRecent: function(interval) { return countRecent(interval) }
  
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

function registerAdmin(data) {
  return axios.post('/admin/save', data)
}

function countByLanguage() {
  return axios.get('/user/countByLanguage')
}

function countByDeviceType() {
  return axios.get('/device/countByFamily')
}

function countByAccountType() {
  return axios.get('/account/countByType')
}

function countRecent(interval) {
  return axios.get('/user/countRecent/' + interval)
}

export default ApiConnection;
