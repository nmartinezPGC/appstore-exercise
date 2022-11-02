import API from "../helpers/config";
import API_ENDPOINTS from "../helpers/endPoints";

const ComentaryService = {
    // New Comentary
    newComentary: ({ comentaryData }) =>
        new Promise((resolve, reject) => {
            console.log(comentaryData);
            const {
                description,
                emisor,
                id_app
            } = comentaryData;

            API.post(
                API_ENDPOINTS.NEW_COMENTARY_OF_APPLICATION + id_app,
                {
                    description,
                    emisor,
                },
            )
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get All Comentaries of Application
    getAllComentariesOfApplication: (application = 0) =>
        new Promise((resolve, reject) => {
            if (!application) {
                API.get(API_ENDPOINTS.LIST_ALL_COMENTARIES_OF_APPLICATION + application)
                    .then((res) => resolve(res))
                    .catch((err) => reject(err));
            }
        }),
};

export default ComentaryService;
