<template>
    <div id="list">
        <slot></slot>
    </div>
</template>
<script>
export default {
    name:"CardList"
}
</script>
<style scoped>
    #list{
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        overflow-y: scroll;
    }
</style>