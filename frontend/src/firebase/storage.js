import {getStorage, ref, uploadBytesResumable, getDownloadURL} from 'firebase/storage'
import firebase from './config'

const storage = getStorage(firebase)

export default {
    upload(r,file){
        const imageRef = ref(storage,r)
        const task = uploadBytesResumable(imageRef,file)
        return task
    }
}