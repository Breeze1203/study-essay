<template>
    <div>Test2</div>
    <button @click="mens">点击发送</button>
</template>

<script> 
import request from '@/api/request';

export default{
    name:'Test2',
    methods:{
        mens(){
            request.menuinit().then(resp=>{
                console.log(resp);
            })
        }
    }
}

</script>