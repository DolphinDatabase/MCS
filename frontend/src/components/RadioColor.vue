<template>
    <div v-for="c in this.colors" :key="c.color">
        <input type="radio" :id="c.name" name="color" :value="c.color"/>
        <label :for="c.name">{{c.name}}</label>
    </div>    
</template>
<script>
export default {
    name:"RadioColor",
    props:{
        colors:Array
    }
}
</script>