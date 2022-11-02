import axios from "axios";
import API from "../helpers/config";
import API_ENDPOINTS from "../helpers/endPoints";

const ApplicationService = {
    // New Deposit
    newDireccion: ({ direccionData }) =>
        new Promise((resolve, reject) => {
            const {
                codigo,
                nombre,
                id_colonia
            } = direccionData;

            API.post(
                API_ENDPOINTS.NEW_DIRECTION,
                {
                    codigo: codigo,
                    nombre: nombre,
                    id_colonia: id_colonia,
                },
                {
                    headers: {
                        "api-token":
                            JSON.parse(localStorage.getItem("api-token")).token || "",
                    },
                }
            )
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get All Categories
    getAllCategories: () =>
        new Promise((resolve, reject) => {
            axios.get(API_ENDPOINTS.LIST_ALL_CATEGORIES)
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get All Applications
    getAllApplications: (category = 0) =>
        new Promise((resolve, reject) => {
            if (category == 0) {
                API.get(API_ENDPOINTS.LIST_ALL_APPLICATIONS)
                    .then((res) => resolve(res))
                    .catch((err) => reject(err));
            } else if (category != 0) {
                API.get(API_ENDPOINTS.LIST_ALL_APPLICATIONS_BY_CATEGORY + category)
                    .then((res) => resolve(res))
                    .catch((err) => reject(err));
            }
        }),

    // Get All Applications by Category
    getAllApplicationsByCategory: (idCategory) =>
        new Promise((resolve, reject) => {
            API.get(API_ENDPOINTS.LIST_ALL_APPLICATIONS_BY_CATEGORY + idCategory)
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get Detail Application
    getDetailApplication: (application) =>
        new Promise((resolve, reject) => {
            API.get(API_ENDPOINTS.DETAIL_APPLICATION + application)
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),
};

export default ApplicationService;
