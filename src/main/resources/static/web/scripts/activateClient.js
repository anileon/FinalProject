Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
         
        }
    },

    created() {

        const params = new Proxy(new URLSearchParams(window.location.search), {

            get: (searchParams, prop) => searchParams.get(prop),
        })
        
        let token = params.token;

        axios.post("http://localhost:8080/api/activateAccount/" + token)
        .then(response =>{
            console.log("Activada perro")
        })
      
    },

    mounted() {
        // Note: do not add parentheses () for this.handleScroll
      
    },

    methods: {

    },
    computed: {



    }


}).mount('#app')
