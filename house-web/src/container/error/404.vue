<template>
  <div>
    <div class="logo">
      <p>OOPS! - Could Not Find it</p>
      <img src="../../assets/image/404.png"/>
      <div class="sub">
        <p><a href="/">返回首页</a></p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Page404',
}
</script>

<style  scoped>
  body {
    margin: 0;
    background: #eaeaea;
  }
  .wrap .logo img {
    width: 350px;
  }

  .wrap .logo p {
    color: #272727;
    font-size: 40px;
    margin-top: 30px;
    margin-bottom: 30px;
  }

  .sub a {
    color: #fff;
    background: #272727;
    text-decoration: none;
    padding: 10px 20px;
    font-size: 13px;
    font-family: arial, serif;
    font-weight: bold;
    -webkit-border-radius: .5em;
    -moz-border-radius: .5em;
    -border-radius: .5em;
  }
</style>
