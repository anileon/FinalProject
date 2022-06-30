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
            
            auxiliar: [],
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

            this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
            this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
            this.arrayMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")
            this.arrayProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
            this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length

            setTimeout(() => {
                let loader = document.querySelector(".bike-loader")
                loader.classList.add("oculto")
            }, 3000);
        })
    },

    methods: {
        borrarCarrito(producto) {
            if (producto.hasOwnProperty('id') && producto.hasOwnProperty('size')) {
                let arrFiltrado = this.arrayDeProductos.filter(obj => obj.id != producto.id)
                let arrayOBJ = this.arrayProductos.filter(obj => obj.idProducto != producto.id)

                localStorage.setItem("productos-carrito", JSON.stringify(arrFiltrado))
                localStorage.setItem("array-productos", JSON.stringify(arrayOBJ))

                this.arrayProductos = arrayOBJ
                this.arrayDeProductos = arrFiltrado
                this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length
                this.total = this.total - this.subtotal(producto.price, producto.cantidad)
            }

            if (producto.hasOwnProperty('id') && producto.hasOwnProperty('model')) {
                let arrFiltrado = this.arrayDeMotos.filter(obj => obj.id != producto.id)
                let arrayOBJ = this.arrayDeMotos.filter(obj => obj.id != producto.id)
                
                localStorage.setItem("motos-carrito", JSON.stringify(arrFiltrado))
                localStorage.setItem("array-motos", JSON.stringify(arrayOBJ))

                this.arrayMotos = arrayOBJ
                this.arrayDeMotos = arrFiltrado
                this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length
                this.total = this.total - this.subtotal(producto.price, producto.cantidad)
            }
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

            if (this.totalCarrito.length < this.productosGeneral) {
                this.totalCarrito.push(total)
            }
            
            if (this.totalCarrito.length <= this.productosGeneral) {
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
            let contacto = document.querySelector(".nav-contact")

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
            if (!contacto.classList.contains("oculto")) {
                contacto.classList.toggle("oculto")
            } 

            element.classList.remove("oculto")
        },

        cerrarNavbar(element){
            let elemento = document.querySelector(element)
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

            let auxiliar = []
            if (this.searchText != "") {
                this.bikes.filter(prod => {
                    if (prod.model.toUpperCase().includes(this.searchText.toUpperCase())) {
                        auxiliar.push(prod)
                        this.auxiliar = auxiliar
                    }
                })
            }

            if (this.precioSeleccionado == "Relevant") {
                this.auxiliar = this.bikes
            }
            if (this.precioSeleccionado == "Least") {
                this.auxiliar = this.bikes.sort((a, b) => a.price - b.price)
            }
            if (this.precioSeleccionado == "Most") {
                this.auxiliar = this.bikes.sort((a, b) => b.price - a.price)
            }


            if (this.ccSleccionado == "995") {
                this.auxiliar = this.auxiliar.filter(bike => bike.displacement == "995cc")
            }
            if (this.ccSleccionado == "1150") {
                this.auxiliar = this.auxiliar.filter(bike => bike.displacement == "1150cc")
            }
            if (this.ccSleccionado == "1200") {
                this.auxiliar = this.auxiliar.filter(bike => bike.displacement == "1200cc")
            }
            if (this.ccSleccionado == "1250") {
                this.auxiliar = this.auxiliar.filter(bike => bike.displacement == "1250cc")
            }
            if (this.ccSleccionado == "1300") {
                this.auxiliar = this.auxiliar.filter(bike => bike.displacement == "1300cc")
            }

        }
    }

}).mount('#app')