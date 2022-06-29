const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('type');

Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            bikes: [],

            scrolled: false,
            searchText: "",
            ccSleccionado: "Any",
            modeloSeleccionado: "All models",
            precioSeleccionado: "Relevant",
            
            productosGeneral: [],
            arrayDeMotos: [],
            totalCarrito: [],
            arrayDeProductos: [],
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        axios.get("/api/motorcycles")
        .then(res => {
            if (getID == "DUCATI") {
                this.bikes = res.data.filter(bike => bike.brandType == "DUCATI")
            } else {
                this.bikes = res.data.filter(bike => bike.brandType == "HARLEY")
            }

            console.log(this.bikes);

            this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
            this.productosGeneral = JSON.parse(localStorage.getItem("carrito") || "[]")
            this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
        })
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

        subtotal(precio, cantidad) {
            let price = precio
            let amount = cantidad
            let total = price * amount

            if (this.totalCarrito.length < this.productosGeneral.length) {
                this.totalCarrito.push(total)
            }
            
            if (this.totalCarrito.length <= this.productosGeneral.length) {
                this.total = this.totalCarrito.reduce((a, b) => a + b, 0)
            }
            return total
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

        toggleCart() {
            let element = document.querySelector(".carrito")
            element.classList.toggle("oculto")
        },

        toggleFilter() {
            let filtro = document.querySelector(".box-de-filtro")
            filtro.classList.toggle("oculto")
        }
    },

    computed: {
        filterChange(){
            this.auxiliar = []

            console.log(this.auxiliar);

            if (this.precioSeleccionado == "Relevant") {
                this.auxiliar = this.productos
            }

            if (this.precioSeleccionado == "Least") {
                this.auxiliar = this.productos.sort((a, b) => a.price - b.price)
            }

            if (this.precioSeleccionado == "Most") {
                this.auxiliar = this.productos.sort((a, b) => b.price - a.price)
            }

            console.log(this.auxiliar);

            if (this.gender == "FEMALE") {
                this.auxiliar = this.productos.filter(prod => prod.genderType == "FEMALE")
            }
            if (this.gender == "MALE") {
                this.auxiliar = this.productos.filter(prod => prod.genderType == "MALE")
            }

            let auxiliar = []
            if (this.searchText != "") {
                this.productos.filter(prod => {
                    if (prod.description.toUpperCase().includes(this.searchText.toUpperCase())) {
                        auxiliar.push(prod)
                        this.auxiliar = auxiliar
                    }
                })
            }

            let aux = []
            if (this.tipoSeleccionado.length > 0) {
                this.productos.forEach(prod => {
                    this.tipoSeleccionado.forEach(check => {
                        prod.type == check ? aux.push(prod) : null
                    })
                })
                this.auxiliar = aux

                if (this.gender == "FEMALE") {
                    this.auxiliar = this.auxiliar.filter(prod => prod.genderType == "FEMALE")
                }
                if (this.gender == "MALE") {
                    this.auxiliar = this.auxiliar.filter(prod => prod.genderType == "MALE")
                }
    
                if (this.precioSeleccionado == "Relevant") {
                    this.auxiliar = this.productos
                }
    
                if (this.precioSeleccionado == "Least") {
                    this.auxiliar = this.auxiliar.sort((a, b) => a.price - b.price)
                }
    
                if (this.precioSeleccionado == "Most") {
                    this.auxiliar = this.auxiliar.sort((a, b) => b.price - a.price)
                }
            }
        }
    }

}).mount('#app')