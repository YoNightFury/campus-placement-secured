import axios from "axios";

const LOGIN_API_BASE_URL = '/login';
const STUDENT_API_BASE_URL = '/student';
const PUBLIC_API_BASE_URL = '/public';
const REGISTRATION_API_BASE_URL = '/registration';


class ApiService {

    fetchStudent() {
        return axios.get(PUBLIC_API_BASE_URL + '/get/allStudent');
    }

    fetchStudentById(Id) {
        return axios.get(PUBLIC_API_BASE_URL + '/profile/' + Id);
    }

    fetchPhoto(id) {
        return axios.get("" + PUBLIC_API_BASE_URL + '/download/photo/'+ id);
    }

    addStudent(student) {
        return axios.post("" + REGISTRATION_API_BASE_URL , student);

    }

   

    loginUser(cred) {
        return axios.post("" + LOGIN_API_BASE_URL , cred);
    }

    editUser(user) {
        let token = sessionStorage.getItem('token');
        return axios.put('/update/details/', user, {headers : { 'Authorization': token }});
    }
    

    fetchResume(id) {
        return axios.get("" + PUBLIC_API_BASE_URL + '/download/resume/' + id, {responseType:"blob"});
    }

    uploadResume(formData) {
        let token = sessionStorage.getItem('token');
        return axios({
            method: "post",
            url: "" + STUDENT_API_BASE_URL + '/add/resume/',
            data: formData,
            headers: { "Content-Type": "multipart/form-data", Authorization :"Bearer "+ token }
        });
    }

    uploadPhoto(formData) {
        let token = sessionStorage.getItem('token');

        return axios({
            method: "post",
            url: "" + STUDENT_API_BASE_URL + '/add/photo/',
            data: formData,
            headers: { 'Content-Type': "multipart/form-data", Authorization:"Bearer "+ token },
        });
    }

    addPlacementDetail(placementDetails){
        let token = sessionStorage.getItem('token');

            return axios.post(""+ STUDENT_API_BASE_URL + `/add/placement/` , placementDetails,{headers : { Authorization:"Bearer "+ token }});
    }
    addProjectDetails(project){
        let token = sessionStorage.getItem('token');

            return axios.post(""+ STUDENT_API_BASE_URL + `/add/project/` , project,{headers : { Authorization:"Bearer "+ token }});
    }
    addQuestion(question){
        let token = sessionStorage.getItem('token');

            return axios.post(""+ STUDENT_API_BASE_URL + `/add/question/` , question,{headers : { Authorization:"Bearer "+ token }});
    }

    updateStudent(student){
        let token = sessionStorage.getItem('token');

            return axios.put(""+ STUDENT_API_BASE_URL + `/update/details/` , student,{headers : { Authorization:"Bearer "+ token }});
    }

    fetchCompanies(){
        return axios.get(PUBLIC_API_BASE_URL+"/fetch/companies");
    }

    fetchPlacement(id){
        return axios.get(PUBLIC_API_BASE_URL+"/fetch/placementdetails/"+id);
    }
    
    fetchQuestions(cid){
        return axios.get(PUBLIC_API_BASE_URL + "/getall/question/"+cid);
    }

    fetchProjectDetails(id){
        return axios.get(PUBLIC_API_BASE_URL+"/fetch/project/"+id);
    }

    deleteStudent(){
        let token = sessionStorage.getItem('token');

        return axios.delete(""+ STUDENT_API_BASE_URL + `/delete`,{headers : { Authorization:"Bearer "+ token }});
    }

}

export default new ApiService();