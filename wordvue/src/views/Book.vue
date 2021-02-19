<template>
  <div class="main">
    <el-row>
      <el-col :span="4">
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @select="handleSelect"
            >
          <el-menu-item index="1">
            <span slot="title">四级</span>
          </el-menu-item>
          <el-menu-item index="2">
            <span slot="title">六级</span>
          </el-menu-item>
          <el-menu-item index="3">
            <span slot="title">考研</span>
          </el-menu-item>
          <el-menu-item index="4">
            <span slot="title">高中</span>
          </el-menu-item>
          <el-menu-item index="5">
            <span slot="title">托福</span>
          </el-menu-item>
          <el-menu-item index="6">
            <span slot="title">雅思</span>
          </el-menu-item>
          <el-menu-item index="7">
            <span slot="title">小学</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="20">
        <el-row class="book_card_row" :gutter="10">
          <el-col :span="5" v-for="book in list" :key="book.id"><book-card :book="book"></book-card></el-col>
        </el-row>
        <el-row>
          <div class="book_page">
            <el-pagination
                background
                layout="prev, pager, next"
                page-size="8"
                :total="page.totalCount">
            </el-pagination>'
          </div>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import bookCard from "@/components/book/bookCard";
import BookCard from "@/components/book/bookCard";
export default {
  name: "Book",
  components: {BookCard},
  comments: {
    bookCard
  },
  data(){
    return{
      list:[],
      page:{
        current:1,
        pageSize:8
      },
      classifyId:0
    }
  },
  mounted() {
    this.selectPage(1,8);
  },
  methods:{
    handleSelect(key, keyPath) {
      this.page.current = 1;
      this.page.pageSize = 8;
      this.classifyId = key;
      this.selectPage(this.page.current,this.page.pageSize);
    },
    selectPage(current,pageSize) {
      this.getRequest("/book?current="+current+"&pageSize="+pageSize+"&classifyId="+this.classifyId).then(resp =>{
        if (resp) {
          this.list = resp.obj.list;
          this.page = resp.obj.page;
        }
      })
    }
  }
}
</script>

<style>
  .main{
    width:80%;
    margin: 10px auto;
    background-color: #ffffff;
  }
  .book_card_row{
    height: 600px;
    padding-top: 20px;
    padding-left: 80px;
    padding-bottom: 20px;
  }
  .book_page{
    float: right;
  }
</style>