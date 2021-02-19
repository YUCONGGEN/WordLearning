<template>
  <div class="main">
    <div class="word">
      <div class="title">
        已经连续学习<span style="font-size: 40px;margin: 0 10px;">{{user.bookDay}}</span>天
      </div>
      <word-card v-if="flag"></word-card>
      <div v-if="flag" class="word_button" @click="studying">
        <span>继续学习</span>
        <img src="https://assets.baydn.com/web_static/words_wordsweb/static/media/button.21bffda9.svg" alt="继续学习">
      </div>
      <div v-else class="word_button" @click="addBook">
        <span>添加单词书</span>
        <img src="https://assets.baydn.com/web_static/words_wordsweb/static/media/button.21bffda9.svg" alt="继续学习">
      </div>
      <div class="word_quoteContainer">
        <div>
          <div class="word_content">
            Growth is the only evidence of life.
          </div>
          <div class="word_translation">
            成长是生命的唯一证据。
          </div>
          <div class="word_author">
            ————John Henry Newman
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import wordCard from "@/components/word/wordCard";
export default {
  name: "Word",
  components: {
    wordCard
  },
  data(){
    return{
      user: JSON.parse(window.sessionStorage.getItem('user')),
      flag: false
    }
  },
  mounted() {
    this.getFlag();
  },
  methods:{
    studying(){
      this.$router.replace("study")
    },
    addBook(){
      this.$router.replace("book")
    },
    getFlag(){
      this.getRequest("/flag").then(resp =>{
        if (resp) {
          this.flag = true;
        }
      })
    }
  }
}
</script>

<style>
  .main{
    width:80%;
    height: 600px;
    margin: 10px auto;
    background-color: #ffffff;
  }
  .word{
    width: 90%;
    margin: 0 auto;
  }
  .word .title{
    padding: 10px 0;
    font-size: 18px;
    color: #333;
    font-weight: 700;
  }
  .word .word_button{
    position: relative;
    width: 300px;
    height: 56px;
    margin: 40px auto;
    cursor: pointer;
  }
  .word .word_button span{
    color: #fff;
    position: absolute;
    z-index: 2;
    left: 50%;
    top: 50%;
    -webkit-transform: translate(-50%,-50%);
    transform: translate(-50%,-50%);
    font-size: 20px;
  }
  .word .word_button img{
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
  }
  .word_quoteContainer{
    border-top: 1px solid #d8dbe4;
    width: 100%;
    margin: 0 auto;
    padding: 25px 0;
  }
  .word_content{
    font-weight: 700;
  }
  .word_translation,.word_content,.word_author{
    font-size: 15px;
    color: #333;
  }
  .word_author{
    text-align: right;
  }
</style>