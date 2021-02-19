import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Word from "@/views/Word";
import Book from "@/views/Book";
import Study from "@/views/Study";
import Login from "@/views/Login";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '/word',
        name: 'Word',
        component: Word
      },
      {
        path: '/book',
        name: 'Book',
        component: Book
      },
      {
        path: '/study',
        name: 'Study',
        component: Study
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
