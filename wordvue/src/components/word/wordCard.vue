<template>
  <div class="card">
    <div class="card_left">
      <div class="book">
        <img class="book_img" :src="book.image" :alt="book.name">
        <div class="book_right">
          <div>
            <div class="book_title">
              <span class="book_name">{{book.name}}</span>
              <img class="book_switchBtn" src="https://assets.baydn.com/web_static/words_wordsweb/static/media/switch.473b2087.svg" alt="切换单词书">
            </div>
            <div class="book_timeWrapper">
              <span class="book_tag">预计完成时间</span>
              <span class="book_time">{{studySet.endTime}}</span>
            </div>
          </div>
          <div>
            <div class="book_progText">
              <span>已完成{{percentage}}%</span><span>{{countWord}}/{{book.count}}词</span>
            </div>
            <el-progress :percentage="percentage" :format="format"></el-progress>
          </div>
        </div>
      </div>
    </div>
    <div class="card_right">
      <div>
        <div class="dailyTask_title">
          — 今日任务 —
        </div>
        <div class="dailyTask_itemsWrapper">
          <div class="dailyTask_item">
            <div class="dailyTask_num">
              {{studySet.newWord}}
            </div>
            <div class="dailyTask_name">
              新词数
            </div>
          </div>
          <div class="dailyTask_item">
            <div class="dailyTask_num">
              {{studySet.reviewWord}}
            </div>
            <div class="dailyTask_name">
              复习单词
            </div>
          </div>
          <div class="dailyTask_item">
            <div class="dailyTask_num">
              {{studySet.dayNewWord}}
            </div>
            <div class="DailyTask_name">
              未学单词
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "wordCard",
  data(){
    return{
      book: {
        image: "https://media-image1.baydn.com/wordmaster_pub_image/qayvad/8f2ec54502a08adc5fbed277fa22939f.2403af2a14c952cf145383d5b4a1c9fd.png?x-oss-process=image/quality,Q_80",
        name: "小学汇总词汇"
      },
      studySet:{
        startTime: "2020年01月01日",
        endTime: "2020年10月01日",
        newWord: 200,
        reviewWord: 200
      },
      countWord: 0
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    format(percentage){
      return '';
    },
    init(){
      this.getRequest("/book/info").then(resp => {
        if (resp) {
          this.book = resp.obj;
          this.studySet = resp.obj.studySet;
        }
      })
      this.getRequest("/word/countWord").then(resp =>{
        if (resp) {
          this.countWord = resp.obj;
        }
      })
    }
  },
  computed: {
    percentage:function (){
      return  Math.round((this.countWord/this.book.count)*100)
    }
  }
}
</script>

<style>
  .card{
    height: 268px;
    border-radius: 20px;
    background-color: #fafafa;
    display: flex;
    align-items: center;
    padding: 0 30px;
  }
  .card_left{

  }
  .book {
    display: flex;
    position: relative;
  }
  .book .book_img{
    width: 143px;
    height: 205px;
    border-radius: 20px;
  }
  .book .book_right{
    width: 360px;
    margin-left: 28px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    position: relative;
  }
  .book .book_right .book_title{
    display: flex;
    align-items: center;
  }
  .book .book_right .book_title .book_name{
    font-size: 20px;
    color: #333;
    font-weight: 700;
  }
  .book .book_right .book_title .book_switchBtn{
    width: 24px;
    height: 21px;
    margin-left: 10px;
  }
  .book .book_right .book_timeWrapper{
    margin-top: 10px;
  }
  .book .book_right .book_timeWrapper .book_tag{
    background-color: #28bea0;
    margin-right: 10px;
  }
  .book_tag{
    color: #fff;
    border-radius: 4px;
    padding: 2px 5px;
    font-size: 16px;
  }
  .book .book_right .book_timeWrapper .book_time{
    font-size: 16px;
    color: #333;
  }
  .book .book_right .book_progText{
    font-size: 16px;
    color: #666;
    width: 100%;
    display: flex;
    justify-content: space-between;
  }
  .card_right{
    flex: 1 1;
  }
  .dailyTask_title{
    font-size: 15px;
    color: #999;
    text-align: center;
    margin-bottom: 20px;
  }
  .dailyTask_itemsWrapper{
    display: flex;
    align-items: center;
    justify-content: space-between;
    text-align: center;
    margin: 0 80px;
  }
  .dailyTask_item .dailyTask_num{
    font-size: 40px;
    color: #333;
    font-weight: 700;
  }
  .dailyTask_item .dailyTask_name{
    font-size: 16px;
    -webkit-columns: #666;
    -moz-columns: #666;
    column-count: #666;
  }
</style>