<script>
import Card from '../src/components/Card.vue';

export default {
  components: {
    Card
  },
  data() {
    return {
      slide: 0,
      sliding: null,

      cardData: [],
      selectedCard: {
        id: null,
        titulo: '',
        autor: '',
        fecha_publicacion: '',
      },

      tituloadd: '',
      autoradd: '',
      fechaPublicacionadd: '',

      showElement: true,

      lastScrollPosition: 0


    }
  },
  mounted() {
    this.fetchData();
    window.addEventListener("scroll", this.onScroll);

  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },
  methods: {
    onScroll() {

      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop;
      console.log(currentScrollPosition);

      if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 500) {
        return;
      }
      this.showElement = currentScrollPosition < this.lastScrollPosition;
      this.lastScrollPosition = currentScrollPosition;
    },
    async fetchData() {
      try {

        this.loading = true

        const options = {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = jsonData;
        this.loading = false
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    async fetchDataAutores() {
      try {
        console.log("entro");


        const options = {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/autor', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = [];
        this.cardData = jsonData;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    async fetchDataFecha() {
      try {
        console.log("entro");


        const options = {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/fechaPublicacion', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = [];
        this.cardData = jsonData;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    async fetchDataImagen() {
      try {
        console.log("entro");


        const options = {
          method: 'GET',
          headers: { 'Content-Type': 'application/json' },
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/imagen', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = [];
        this.cardData = jsonData;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },

    async fetchDeleteCard() {
      try {


        const options = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
        };

        const response = await fetch(`http://127.0.0.1:8080/api-examenu2/libro/${this.selectedCard.id}`, options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = jsonData;
        this.fetchData();
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    async fetchUpdateCard() {
      try {

        this.loading = true

        const options = {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.selectedCard)
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = jsonData;
        this.fetchData();
        this.$bvModal.hide('modal-edit');
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    async fetchAddNoImage() {
      try {

        this.loading = true

        const options = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            titulo: this.tituloadd,
            autor: this.autoradd,
            fecha_publicacion: this.fechaPublicacionadd
          })
        };

        const response = await fetch('http://127.0.0.1:8080/api-examenu2/libro/no-image', options)
          .then(response => response.json())
          .then(response => {
            return response;
          })
          .catch(err => console.error(err));


        const jsonData = response.data;
        console.log(jsonData);
        this.cardData = jsonData;
        this.fetchData();
        this.$bvModal.hide('modal-add');
        this.tituloadd = '';
        this.autoradd = '';
        this.fechaPublicacionadd = '';
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    handleDrop(event) {
      event.preventDefault();
      const cardEdit = event.dataTransfer.getData('card');
      const cardEditObject = JSON.parse(cardEdit);
      console.log(cardEditObject);
      this.selectedCard = cardEditObject;
      this.$bvModal.show('modal-edit');
    },
    handleDropDelete(event) {
      event.preventDefault();
      const cardDelete = event.dataTransfer.getData('card');
      const cardDeleteObject = JSON.parse(cardDelete);
      console.log(cardDeleteObject);
      this.selectedCard = cardDeleteObject;

      this.fetchDeleteCard();
    },

    resetSelectedCard() {
      this.selectedCard = {
        id: null,
        titulo: '',
        autor: '',
        fecha_publicacion: '',
      }
    },
    showModalAdd() {
      this.$bvModal.show('modal-add');
    },
    resetSelectedCardAdd() {
      this.tituloadd = '';
      this.autoradd = '';
      this.fechaPublicacionadd = '';
    }

  }
}
</script>

<template>
  <div>

    <b-container>

      <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators background="#ababab"
        img-width="1024px" img-height="480px" style="text-shadow: 1px 1px 2px #333;" @sliding-start="this.sliding = true"
        v-show="showElement">
        <b-carousel-slide caption="First slide" text="Nulla vitae elit libero, a pharetra augue mollis interdum."
          img-src="https://picsum.photos/1024/480/?image=52"></b-carousel-slide>

      </b-carousel>

    </b-container>

    <b-container>

      <b-row class="mt-5">
        <b-col cols="8">
          <b-row>
            <b-col cols="4">
              <b-button size="lg" variant="primary" @click="fetchDataAutores">Ordenar por Autor</b-button>
            </b-col>
            <b-col cols="4">
              <b-button size="lg" variant="primary" @click="fetchDataFecha">Ordenar por Fecha</b-button>
            </b-col>
            <b-col cols="4">
              <b-button size="lg" variant="primary" @click="fetchDataImagen">Mostrar si tienen imagen</b-button>
            </b-col>
          </b-row>
          <transition-group name="bounce" tag="b-row">
            <b-col v-for="(item, index) in cardData" :key="item.id" cols="4">
              <Card :data="item" />
            </b-col>
          </transition-group>


        </b-col>

        <b-col cols="4"
          style="display: flex; flex-direction:column;justify-content:space-between; align-items:center; padding-top:100px; padding-bottom:100px;max-height:650px;">
          <div style="padding:30px; background-color:yellow; border-radius:20px " @click="showModalAdd" class="divider">
            Añadir</div>
          <div
            style="padding:30px; background-color:rgb(0, 187, 255); border-radius:20px;  border: 2px dashed rgb(165, 165, 165);"
            @dragover.prevent @drop="handleDrop" @dragenter.prevent>Modificar</div>
          <div
            style="padding:30px; background-color:rgb(233, 62, 62); border-radius:20px; border: 2px dashed rgb(165, 165, 165);"
            @dragover.prevent @drop="handleDropDelete" @dragenter.prevent>Eliminar</div>
        </b-col>
      </b-row>

    </b-container>


    <b-modal id="modal-edit" v-if="selectedCard" @hidden="resetSelectedCard" hide-footer hide-header-close>
      <template #modal-title>Editar</template>
      <form @submit.prevent="fetchUpdateCard">
        <b-form-group label="Titulo" label-for="tituloEditar">
          <b-form-input id="tituloEditar" v-model="selectedCard.titulo" required></b-form-input>
        </b-form-group>
        <b-form-group label="Autor" label-for="autorEditar">
          <b-form-input id="autorEditar" v-model="selectedCard.autor" required></b-form-input>
        </b-form-group>
        <b-form-group label="Fecha Publicación" label-for="fechaPublicacionEditar">
          <b-form-input type="text" id="fechaPublicacionEditar" v-model="selectedCard.fecha_publicacion"
            required></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary" style="margin-top: 30px;">Guardar Cambios</b-button>
      </form>
    </b-modal>

    <b-modal id="modal-add" @hidden="resetSelectedCardAdd" hide-footer hide-header-close>
      <template #modal-title>Añadir</template>
      <form @submit.prevent="fetchAddNoImage">
        <b-form-group label="Titulo" label-for="tituloAdd">
          <b-form-input id="tituloAdd" required v-model="tituloadd"></b-form-input>
        </b-form-group>
        <b-form-group label="Autor" label-for="autorAdd">
          <b-form-input id="autorAdd" required v-model="autoradd"></b-form-input>
        </b-form-group>
        <b-form-group label="Fecha Publicación" label-for="fechaPublicacionAdd">
          <b-form-input type="text" id="fechaPublicacionAdd" required v-model="fechaPublicacionadd"></b-form-input>
        </b-form-group>
        
        <!-- <b-form-group label="¿Quieres añadir una imagen?" label-for="imagenAdd" style="margin-top:40px" >
          <b-form-checkbox id="imagenAdd" v-model="imagenadd" disabled>Añadir Imagen</b-form-checkbox>
        </b-form-group>
        <b-form-group label="Imagen" label-for="imagenAdd" >
          <b-form-file id="imagenAdd" required v-model="imagenadd" disabled></b-form-file>
        </b-form-group> -->
       

        <b-button type="submit" variant="primary" style="margin-top: 30px;">Añadir Libro</b-button>
      </form>
    </b-modal>






  </div>
</template>

<style scoped>
#dropzone {
  cursor: pointer;
}

.divider:hover {
  cursor: pointer;
}
</style>
