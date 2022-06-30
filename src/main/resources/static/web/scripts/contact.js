Vue.createApp({
    data() {
        return {
            scrolled: false,
            searchText: "",
            ccSleccionado: "Any",
            modeloSeleccionado: "All models",
            precioSeleccionado: "Relevant",

            arrayDeProductos: [],
            arrayDeMotos: [],
            productosDelCarrito: [],
            productosGeneral: [],
            totalCarrito: [],
            idProducto: "",
            total: "",
        }
    },

    mounted() {
        window.addEventListener('scroll', this.handleScroll);
    },

    created() {
        this.arrayDeProductos = JSON.parse(localStorage.getItem("productos-carrito") || "[]")
        this.arrayDeMotos = JSON.parse(localStorage.getItem("motos-carrito") || "[]")
        this.arrayMotos = JSON.parse(localStorage.getItem("array-motos") || "[]")
        this.arrayProductos = JSON.parse(localStorage.getItem("array-productos") || "[]")
        this.productosGeneral = this.arrayDeProductos.length + this.arrayDeMotos.length

        setTimeout(() => {
            let loader = document.querySelector(".bike-loader")
            loader.classList.add("oculto")
        }, 5000);
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
        
        toggleNavbar() {
            let nav = document.querySelector(".navbar")
            let btn = document.querySelector(".nav-menu-btn")

            nav.classList.toggle("oculto")

            console.log(btn.textContent == "menu");
            if (btn.textContent == "menu") {
                btn.textContent = "close"
            } else if (btn.textContent == "close") {
                btn.textContent = "menu"
            }
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

        toggleFilter() {
            let filtro = document.querySelector(".box-de-filtro")
            filtro.classList.toggle("oculto")
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

        toggleCart() {
            let element = document.querySelector(".carrito")
            element.classList.toggle("oculto")
        },
    },

}).mount('#app')

const body = document.querySelector("body");
const modal = document.querySelector(".modal");
const modalButton = document.querySelector(".modal-button");
const closeButton = document.querySelector(".close-button");
const scrollDown = document.querySelector(".scroll-down");
let isOpened = false;

const openModal = () => {
    modal.classList.add("is-open");
    body.style.overflow = "hidden";
};

const closeModal = () => {
    modal.classList.remove("is-open");
    body.style.overflow = "initial";
};

window.addEventListener("scroll", () => {
    if (window.scrollY > window.innerHeight / 3 && !isOpened) {
        isOpened = true;
        scrollDown.style.display = "none";
        openModal();
    }
});

modalButton.addEventListener("click", openModal);
closeButton.addEventListener("click", closeModal);

document.onkeydown = evt => {
    evt = evt || window.event;
    evt.keyCode === 27 ? closeModal() : false;
};