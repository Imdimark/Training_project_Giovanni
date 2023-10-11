import axios from 'axios';

const EMPLOYEE_API_REST_URL = "http://localhost:8080/company/getPersons";

class APIService {
    
    getEmployees(){
        return axios.get(EMPLOYEE_API_REST_URL);
    }

}

export default new APIService();