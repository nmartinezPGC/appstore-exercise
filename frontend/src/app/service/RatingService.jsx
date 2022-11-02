import API from "../helpers/config";
import API_ENDPOINTS from "../helpers/endPoints";

const RatingService = {
    // New Rating
    newRating: ({ ratingData }) =>
        new Promise((resolve, reject) => {
            const {
                rating,
                emisor,
                id_app
            } = ratingData;

            API.post(
                API_ENDPOINTS.NEW_RATING_OF_APPLICATION + id_app,
                {
                    rating,
                    emisor,
                },
            )
                .then((res) => resolve(res))
                .catch((err) => reject(err));
        }),

    // Get All Ratings of Application
    getAllRatingsOfApplication: (application = 0) =>
        new Promise((resolve, reject) => {
            if (!application) {
                API.get(API_ENDPOINTS.LIST_ALL_RATING_OF_APPLICATION + application)
                    .then((res) => resolve(res))
                    .catch((err) => reject(err));
            }
        }),
};

export default RatingService;
