import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: BASE_URL,
});

api.interceptors.request.use((config) => {
  const username = localStorage.getItem('username');
  const password = localStorage.getItem('password');

  if (username && password) {
    const token = btoa(`${username}:${password}`);
    config.headers['Authorization'] = `Basic ${token}`;
  }

  return config;
}, (error) => {
  return Promise.reject(error);
});

export default api;


