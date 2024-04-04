import axios from "axios";

// const baseURL = process.env.NODE_ENV === "development" ? "/api" : `${window.location.origin}`;

const instance = axios.create({
  baseURL: "/api",
  timeout: 5000
});

instance.interceptors.response.use(
  res => {
    return res.data;
  },
  err => {
    return Promise.reject(err);
  }
);

export default instance;
