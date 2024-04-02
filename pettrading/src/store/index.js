import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    uid: window.sessionStorage.getItem('uid'),
    // uid: JSON.parse(localStorage.getItem("uid")),
    show: false,
    logshow: false,
    regshow: false,
    showchat: false,
    avatar: window.sessionStorage.getItem('avatar'),
    buypage: window.sessionStorage.getItem('buypage'),
    sellpage: window.sessionStorage.getItem('sellpage'),
    
    // webscoketnotice: window.sessionStorage.getItem('webscoketnotice'),
    webscoketnotice: null,
  },
  mutations: {
    setuid(state, data) {
      // localStorage.setItem('uid',JSON.stringify(data));
      state.uid = data;
      window.sessionStorage.setItem('uid',data);
    },
    setavatar(state, data) {
      state.avatar = data;
      window.sessionStorage.setItem('avatar',data);
    },
    setbuypage(state,data) {
      state.buypage = data;
      window.sessionStorage.setItem('buypage',data);
    },
    setsellpage(state,data) {
      state.sellpage = data;
      window.sessionStorage.setItem('sellpage',data);
    },
    setwebscoketnotice(state,data) {
      state.webscoketnotice = data;
      // window.sessionStorage.setItem('webscoketnotice',data);
    }
  },
  actions: {
  },
  modules: {
  }
})

export default store;