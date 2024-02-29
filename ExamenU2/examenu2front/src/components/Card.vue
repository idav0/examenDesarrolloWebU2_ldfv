<template>
    <div>

        <b-card :title="`Libro #${data.id}`"
        :img-src=" data.imagen ? data.imagen : 'https://static.vecteezy.com/system/resources/previews/013/089/833/original/blank-white-book-png.png'"
            img-alt="Image" img-top tag="article" style="max-width: 20rem; margin: 20px;" class="mb-2 shadow-card"
            draggable="true" @dragstart="handleDragStart($event, data)">
            <p class="card-text"><b>Título:</b> {{ data.titulo }}</p>
            <p class="card-text"><b>Autor:</b> {{ data.autor }}</p>
            <p class="card-text"><b>Fecha Publicación:</b> {{ data.fechaPublicacion }}</p>
        </b-card>

    </div>
</template>
  
<script>
export default {
    props: {
        data: {
            type: Object,
            required: true
        }
    },
    methods: {
        handleDragStart(evt, data) {
            const dataObject = {
                id: this.data.id,
                titulo: this.data.titulo,
                autor: this.data.autor,
                fecha_publicacion: this.data.fechaPublicacion
            }
            evt.dataTransfer.setData('card', JSON.stringify(dataObject));
        }
    }
}
</script>

<style scoped>
.shadow-card {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    transition: 0.3s;
}

.shadow-card:hover {
    box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 18px 60px 0 rgba(0, 0, 0, 0.19);
}
</style>