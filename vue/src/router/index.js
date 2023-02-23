import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/Layout";
import Login from "@/views/login/Login";
import Cookies from "js-cookie";

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
            {
                path: 'videoAdd',
                name: 'AddVideo',
                component: () => import('@/views/video/videoAdd')
            },
            {
                path: 'editVideo',
                name: 'editVideo',
                component: () => import('@/views/video/videoEdit')
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
            //========故障管理========
            {
                path:'trouble',
                name: 'Trouble',
                component: () => import('@/views/trouble/trouble')
            },
            //========用户管理========
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
            {
                path: 'person',
                name: 'Person',
                component: () => import('@/views/user/Person')
            },
            //========权限管理========
            {
                path: 'role',
                name: 'Role',
                component: () => import('@/views/role/Role')
            },
            //========车辆管理========
            {
                path: 'car',
                name: 'Car',
                component: () => import('@/views/car/car')
            },
            {
                path: 'carAdd',
                name: 'CarAdd',
                component: () => import('@/views/car/carAdd')
            },
            {
                path: 'carEdit',
                name: 'CarEdit',
                component: () => import('@/views/car/carEdit')
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login') next()
    const admin = Cookies.get("admin")
    if (!admin && to.path !== '/login') return next("/login")  // 强制退回到登录页面
    // 访问 /home 的时候，并且cookie里面存在数据，这个时候我就直接放行
    next()
})

export default router
