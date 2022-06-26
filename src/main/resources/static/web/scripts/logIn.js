const {
    createApp
} = Vue

createApp({
    data() {
        return {
            message: 'Hello Vue!'
        }
    },

    methods: {
        switchSignUp(){
            const container = document.getElementById('container');
            container.classList.add("right-panel-active");
        },

        switchLogIn(){
            const container = document.getElementById('container');
            container.classList.remove("right-panel-active");
        }
    },
}).mount('#app')