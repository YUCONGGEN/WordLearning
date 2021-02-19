<template>
  <div>
    <div class="book_BookWrap" @click="findBookInfo">
      <div class="book_coinsWrap">
        <img class="book_image" :src="book.image" :alt="book.name">
      </div>
      <div class="book_title">
        {{ book.name }}
      </div>
      <div class="book_wordsNum">
        {{ book.count }}词
      </div>
    </div>
    <div>
      <el-dialog
          title="设置任务量"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
        <el-form ref="form" :model="studySet" label-width="80px">
          <el-form-item label="每日新词">
            <el-select v-model="studySet.newWord" filterable placeholder="请选择任务量">
              <el-option v-for="item in book.count" :key="item"  :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="每日复习">
            <el-select v-model="studySet.reviewWord" filterable placeholder="请选择任务量">
              <el-option v-for="item in book.count" :key="item"  :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addStudy">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "bookCard",
  props:['book'],
  data(){
    return{
      dialogVisible: false,
      options: 92,
      studySet:{

      }
    }
  },
  methods: {
    findBookInfo(){
      this.$confirm('此操作将丢失原有学习进度, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.dialogVisible = true;
      }).catch(() => {
      });
    },
    addStudy(){
      this.postRequest("/book/studySet/"+this.book.id,this.studySet).then(resp =>{
        if (resp) {
          this.$router.replace("/word");
        }
      })
    }
  }
}
</script>

<style>
  .book_BookWrap{
    display: inline-block;
    text-align: left;
    width: 144px;
    cursor: pointer;
  }
  .book_BookWrap .book_coinsWrap{
    position: relative;
    border-radius: 10px;
    overflow: hidden;
  }
  .book_BookWrap .book_coinsWrap .book_image{
    width: 144px;
    height: 206px;
    display: block;
  }
  .book_BookWrap .book_title{
    font-family: PingFangSC;
    font-size: 14px;
    font-weight: 600;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    margin: 6px 0;
  }
  .book_BookWrap .book_wordsNum{
    font-size: 14px;
    letter-spacing: -.09px;
    color: #666;
  }
</style>