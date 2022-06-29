Vue.createApp({
  data() {
      return {


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
      }
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
