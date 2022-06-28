Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            productos: [],

            scrolled: false,
            searchText: "",
            ccSleccionado: "Any",
            modeloSeleccionado: "All models",
            precioSeleccionado: "Relevant",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {

    },

    methods: {
        addFavorite(id){
            console.log(id);
            let unFavorite = document.querySelector("#unFavoriteMobile" + id)
            let favorite = document.querySelector("#favoriteMobile" + id)
            unFavorite.classList.remove("favoriteMobileChecked")
            unFavorite.classList.add("favoriteMobileUnChecked")
            favorite.classList.remove("favoriteMobileUnChecked")
            favorite.classList.add("favoriteMobileChecked")
        },
        removeFavorite(id){
            let unFavorite = document.querySelector("#unFavoriteMobile" + id)
            let favorite = document.querySelector("#favoriteMobile" + id)
            favorite.classList.remove("favoriteMobileChecked")
            favorite.classList.add("favoriteMobileUnChecked")
            unFavorite.classList.remove("favoriteMobileUnChecked")
            unFavorite.classList.add("favoriteMobileChecked")
        },
        goBikePage(){
            let bike = document.querySelector(".cartaCatalogo")
            window.location.href="/web/bike.html"
        },

        toggleNavbar(){
            let nav = document.querySelector(".navbar")
            let btn = document.querySelector(".nav-menu-btn")

            nav.classList.toggle("oculto")

            console.log(btn.textContent == "menu");
            if(btn.textContent == "menu") {
                btn.textContent = "close"
            }else if(btn.textContent == "close") {
                btn.textContent = "menu"
            }
        },

        toggleNavItem(target){
            let element = document.querySelector(target)
            let moto = document.querySelector(".nav-motos")
            let hombre = document.querySelector(".nav-hombre")
            let mujer = document.querySelector(".nav-mujer")
            let experiencia = document.querySelector(".nav-experiencia")

            element.classList.toggle("oculto")

            if (!moto.classList.contains("oculto")) {
                moto.classList.toggle("oculto")
            }
            if (!hombre.classList.contains("oculto")) {
                hombre.classList.toggle("oculto")
            }
            if (!mujer.classList.contains("oculto")) {
                mujer.classList.toggle("oculto")
            }
            if (!experiencia.classList.contains("oculto")) {
                experiencia.classList.toggle("oculto")
            }

            element.classList.toggle("oculto")
        },

        cerrarNavbar(element){
            let elemento = element.target.parentElement.parentElement

            elemento.classList.add("oculto")
        },

        handleScroll() {
            this.scrolled = window.scrollY > 0;
        },

        toggleFilter() {
            let filtro = document.querySelector(".box-de-filtro")
            filtro.classList.toggle("oculto")
        },

        obtenerURL(arr){
            log
        }
    },

}).mount('#app')