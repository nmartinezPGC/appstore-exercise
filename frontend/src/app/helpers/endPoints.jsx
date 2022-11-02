/**
 * Mapeo de las URI de la API
 */
const API_BASE_MSVC_APPLICATIONS = "http://localhost:8001/api/v1";
const API_BASE_MSVC_DOWNLOADS = "http://localhost:8004/api/v1";

const API_ENDPOINTS = {
    // Module Applications
    LIST_ALL_CATEGORIES: API_BASE_MSVC_APPLICATIONS + "/category",
    LIST_ALL_APPLICATIONS: API_BASE_MSVC_APPLICATIONS + "/application",
    DETAIL_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/",
    LIST_ALL_APPLICATIONS_BY_CATEGORY: API_BASE_MSVC_APPLICATIONS + "/application/category/",

    // Module Comentaries
    NEW_COMENTARY_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/create-comentary/",
    LIST_ALL_COMENTARIES_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/",

    // Module Ratings
    NEW_RATING_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/create-rating/",
    LIST_ALL_RATING_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/",

    // Module Downloads
    NEW_DOWNLOAD_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/create-download/",
    COUNT_DOWNLOAD_OF_APPLICATION: API_BASE_MSVC_DOWNLOADS + "/count-downloads-by-application/",
    LIST_ALL_DOWNLOAD_OF_APPLICATION: API_BASE_MSVC_APPLICATIONS + "/application/",
}

// export default API;
export default API_ENDPOINTS;