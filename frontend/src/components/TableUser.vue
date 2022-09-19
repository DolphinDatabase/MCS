<template>
    <el-table :data="tableData" style="width: 100%; font-size: 20px;">
      <el-table-column prop="name" label="Nome" />
      <el-table-column prop="email" label="E-mail" />
      <el-table-column prop="roles" label="Tipo" />
      <el-table-column fixed="right" label="Ações" width="100">
        <template #default="scope">
            <el-button link style="color: var(--color-primary-variant)" @click="edit(scope.row.id)">
                <el-icon :size="20">
                    <Edit/>
                </el-icon>
            </el-button>
            <el-button link >
                <el-icon :size="20" style="color: var(--color-red)" @click="deleteUser(scope.row)">
                    <DeleteFilled />
                </el-icon>
            </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- Edit Modal -->
    <el-dialog v-model="this.editmodal" title="Editar Usuário" width="40%" align-center>
        <el-form label-position="top" :model="this.editForm" ref="editForm">
            <el-form-item label="Nome">
                <el-input v-model="this.editForm.name" />
            </el-form-item>
            <el-form-item label="E-mail">
                <el-input v-model="this.editForm.email"/>
            </el-form-item>
        </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="this.editmodal = false">Cancelar</el-button>
          <el-button type="primary" @click="submitForm()">Salvar</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- Delete Modal -->
    <el-dialog v-model="this.deletemodal" title="Excluir Usuário" width="40%" align-center>
        <h3>Tem certeza que deseja excluir o usuário {{this.deleteForm.name}} ?</h3>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="this.deletemodal = false">Cancelar</el-button>
          <el-button type="primary" @click="submitDelete()">Deletar</el-button>
        </span>
      </template>
    </el-dialog>
</template>
<script>
import { ElTable, ElTableColumn, ElButton, ElIcon } from 'element-plus'
export default {
    name:"MainTable",
    components:{
        ElTable,
        ElTableColumn,
        ElButton,
        ElIcon
    },
    data: function(){
        return{
            tableData: null,
            editmodal:false,
            editForm:null,
            deletemodal:false,
            deleteForm:null
        }
    },
    methods:{
        edit(scope){
            this.editForm = this.$store.getters.getUsuarioById(scope)
            this.editmodal = true
        },
        deleteUser(scope){
            this.deleteForm = scope
            this.deletemodal = true
        },
        async submitForm(){
            await this.$store.dispatch("updateUsuario",this.editForm)
            await this.$store.dispatch("listUsuarios")
            this.tableData = this.$store.getters.getAllUsuarios
            this.editForm = {name:'',email:''}
            this.editmodal = false
        },
        async submitDelete(){
            await this.$store.dispatch("deleteUsuario",this.deleteForm.id)
            await this.$store.dispatch("listUsuarios")
            this.tableData = this.$store.getters.getAllUsuarios
            this.deletemodal = false
        }
    },
    async mounted(){
        await this.$store.dispatch("listUsuarios")
        this.tableData = this.$store.getters.getAllUsuarios
    }
}
</script>