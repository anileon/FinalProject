const urlParams = new URLSearchParams(window.location.search);
const getID = urlParams.get('id');

Vue.createApp({
    data() {
        return {
            message: 'Hello Vue!',
            producto: [],

            scrolled: false,
            searchText: "",
            cantidad: 1,
            gender: "",
            auxiliar: [],
            tipoSeleccionado: [],
            precioSeleccionado: "Relevant",
            arrayDeProductos: [],

            productosGeneral: [],
            arrayDeMotos: [],
            totalCarrito: [],
            idProducto: "",
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        this.productosGeneral = JSON.parse(localStorage.getItem("carrito") || "[]")
        this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
        this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
        this.motosVenta = JSON.parse(localStorage.getItem("array-motos") || "[]")
        this.productosVenta = JSON.parse(localStorage.getItem("array-productos") || "[]")
    },

    methods: {
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

        realizarCompra() {
            let obj = {
                products: this.productosVenta,
                motors: this.motosVenta
            }
            
            axios.post("/api/comprar", obj)
                .then(res => console.log("FUNCIONA"))
                .catch(err => console.log(err))

            localStorage.clear()
            window.location.reload()
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
    },

    computed: {
        
    }

}).mount('#app')