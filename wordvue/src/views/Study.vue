<template>
  <div class="main">
    <div class="study">
      <div>
        <span class="study_word">{{ word.name }}</span>
      </div>
      <div class="study_content">
        <div class="study_audioWrap">
          UK
          <span class="study_phonetic">
            {{ word.phonetic[0] }}
          </span>
        </div>
        <div class="study_audioWrap">
          US
          <span class="study_phonetic">
            {{ word.phonetic[1] }}
          </span>
        </div>
      </div>
      <div class="study_foot" v-show="!studyBtnBox">
        <hr/>
        <div class="word_study_content">
          <div style="text-align: left;margin-bottom: 30px">解释：<span v-for="explain in word.explain">{{explain}} </span></div>
          <div style="text-align: left;margin-bottom: 10px">例句：</div>
          <div v-for="example in word.example" style="margin-bottom: 10px">{{example}}</div>
        </div>
      </div>
      <div class="study_btnBox">
        <div class="study_option study_green" @click="knowChange" v-show="studyBtnBox">
          <div class="study_title">1.</div>
          <div class="study_content2">认识</div>
        </div>
        <div class="study_option study_red" v-show="studyBtnBox" @click="studyBtnBox=false">
          <div class="study_title">2.</div>
          <div class="study_content2">不认识</div>
        </div>
        <div class="study_option study_green" @click="wordChange" v-show="!studyBtnBox">
          <div class="study_title">1.</div>
          <div class="study_content2">下一个</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Study",
  data(){
    return{
      studyBtnBox: true,
      words: [],
      word:{},
      index: 0
    }
  },
  mounted() {
    this.init();
  },
  methods:{
    init(){
      this.studyBtnBox = true;
      this.getRequest("/word/study").then(resp => {
        if (resp) {
          if (!resp.obj || resp.obj.length==0) {
            this.$alert('今日学习任务已完成', '提示', {
              confirmButtonText: '确定',
              center: true,
              callback: action => {
                this.$router.replace("/word")
              }
            });
          } else {
            this.words = resp.obj;
            this.index = 0;
            this.word = this.words[this.index];
          }
        }
      })
    },
    wordChange(){
      this.studyBtnBox = true;
      this.index++;
      if (this.index<this.words.length) {
        this.word = this.words[this.index]
      } else {
        this.init();
      }
    },
    knowChange(){
      this.studyBtnBox = false;
      this.putRequest("/word/study/"+this.word.id);
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
  .study{
    width: 60%;
    margin: 0 auto;
    height: 100px;
    text-align: center;
  }
  .study_word{
    display: block;
    padding-top: 50px;
    padding-bottom: 20px;
    font-size: 38.5px;
  }
  .study .study_audioWrap{
    display: flex;
    align-items: center;
    margin: 0 20px;
  }
  .study .study_phonetic{
    font-size: 18px;
    color: #999;
    margin: 0 10px;
  }
  .study .study_content{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 40px;
    position: relative;
    margin-bottom: 20px;
  }
  .study_btnBox{
    text-align: center;
    margin-bottom: 200px;
  }
  .study_option{
    width: 700px;
    min-height: 55px;
    display: inline-flex;
    border: 1px solid #ddd;
    cursor: pointer;
  }
  .study_title{
    flex-shrink: 0;
    justify-content: center;
    border-right: 1px solid #ddd;
    width: 35px;
    background-image: url(data:image/png;base64,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);
  }
  .study_content2{
    padding: 4px 15px;
  }
  .study_title,.study_content2{
    display: flex;
    align-items: center;
    color: #000;
  }
  .study_btnBox .study_green:hover{
    background-color: #209e85;
  }
  .study_btnBox .study_red:hover{
    background-color: #f4434b;
  }
  .word_study_content{
    width: 60%;
    margin: 0 auto;
  }
</style>