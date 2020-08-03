<template>
  <div id="app">
    <header>
      <img id="mainlogo" src="@/assets/img/logocrop.png" alt="bilbi text logo">
    </header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

#app {
  background-color: #c7e5e3;
  font-family: Arial, Helvetica, sans-serif;
  height: 100vh;
}

header {
  text-align: center;
}

a {
  text-decoration: none;
}

</style>
