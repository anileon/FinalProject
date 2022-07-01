const {
    createApp
} = Vue

createApp({
    data() {
        return {
            firstName:"",
            lastName:"",
            email:"",
            password:""
        }
    },

    created() {
        setTimeout(() => {
            let loader = document.querySelector(".bike-loader")
            loader.classList.add("oculto")
        }, 5000);
    },

    methods: {
        register(){
            axios.post(`/api/clients`, `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)
            .then(response =>{
                Swal.fire('Mail Sent, verify with email')
                .then(respuesta => window.location.href = '/web/log-in.html')
            })
        },

        logIn(){

            if(this.email.includes("@admin")){
                axios.post('/api/login',`email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(respuesta => Swal.fire('Welcome Back Admin'))
               
                .then(response => window.location.href = '/web/bike-admin.html')
            }else{
                axios.post('/api/login',`email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response =>{
                    Swal.fire('Logged succefull, you will be redirected to the trolley')
                    .then(respuesta => window.location.href = '/web/trolley.html')
                })
            }


         
        },
        
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