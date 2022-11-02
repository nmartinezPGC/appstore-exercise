import API from "../helpers/config";
import API_ENDPOINTS from "../helpers/endPoints";

const DownloadService = {
    // New Download
    newDownload: ({ downloadData }) =>
        new Promise((resolve, reject) => {
            const {
                nombre_app,
                id_app
            } = downloadData;

            API.post(
                API_ENDPOINTS.NEW_DOWNLOAD_OF_APPLICATION + id_app,
                {
                    applicationName: nombre_app,
                },
            )
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get All Downloads of Application
    getAllDownloadsOfApplication: (application = 0) =>
        new Promise((resolve, reject) => {
            if (!application) {
                API.get(API_ENDPOINTS.LIST_ALL_DOWNLOAD_OF_APPLICATION + application)
                    .then((res) => resolve(res))
                    .catch((err) => reject(err));
            }
        }),

    // Get Count Download by Application
    getCountDownloadApplication: (application) =>
        new Promise((resolve, reject) => {
            API.get(API_ENDPOINTS.COUNT_DOWNLOAD_OF_APPLICATION + application)
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),
};

export default DownloadService;
