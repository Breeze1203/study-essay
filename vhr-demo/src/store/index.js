import {createStore} from 'vuex'

export default createStore({
    state: {
        routes:[]
    },
    // 对vuex里面的数据进行过滤
    getters: {},
    // 通过提交更改state里面的数据
    mutations: {
        // 将菜单数据存入state中
        menu(state,data){
            state.routes=data;
        }
    },
    actions: {},
    modules: {}
})
