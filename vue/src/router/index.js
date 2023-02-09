import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout";
import Login from "@/views/login/Login";

Vue.use(VueRouter)

const routes = [
    //   =========登录========
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    //   =========主页========
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: '/home',
        //=========子路由========
        children:[
            //========首页========
            {
                path: 'home',
                name: 'Home',
                component: () => import('@/views/Home/Home')
            },
            //========视频管理========
            {
                path: 'video',
                name: 'Video',
                component: () => import('@/views/video/video')
            },
            //========电站管理========
            {
                path: 'info',
                name: 'Info',
                component: () => import('@/views/station/station')
            },
            {
                path: 'troubleAdd',
                name: 'TroubleAdd',
                component: () => import('@/views/station/trouble')
            },
            {
                path:'editStation',
                name: 'EditStation',
                component: () => import('@/views/station/stationEdit')
            },
            {
                path:'addStation',
                name: 'AddStation',
                component: () => import('@/views/station/stationAdd')
            },
            {
                path:'trouble',
                name: 'Trouble',
                component: () => import('@/views/trouble/trouble')
            },
            {
                path:'user',
                name: 'UserList',
                component: () => import('@/views/user/user')
            },
            {
                path:'userAdd',
                name: 'UserAdd',
                component: () => import('@/views/user/userAdd')
            },
            {
                path:'userEdit',
                name: 'UserEdit',
                component: () => import('@/views/user/userEdit')
            },
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
