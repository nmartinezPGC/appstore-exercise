import React, { useEffect, useState } from 'react'
import ApplicationService from '../../service/ApplicationService';

export const Category = ({ onSelectCategory }) => {
    // States declarations
    const [categories, setCategories] = useState([]);

    // Function All Categories
    const getAllCategories = async () => {
        await ApplicationService.getAllCategories()
            .then((resp) => {
                const { data } = resp;
                const categories = data.map((cat) => {
                    return {
                        id_categoria: cat.id,
                        nombre_categoria: cat.name,
                    };
                });
                // Cambia el estado
                setCategories(categories);
            })
            .catch((err) => {
                alert(err);
            });
    };

    // Get All Categories
    useEffect(() => {
        getAllCategories();
    }, []);

    // Methods of select
    const handleInputChange = (event) => {
        onSelectCategory(event.target.value);
    };

    return (
        <>
            <div className="input-group mb-3">
                <div className="input-group-prepend">
                    <label className="input-group-text">
                        Categoría
                    </label>
                </div>
                <select className="custom-select" id="inputGroupSelect01" name="id_categoria" onChange={handleInputChange}>
                    <option key={0} value="0" >Selecciona una categoría ...</option>
                    {categories.map((cat) => (
                        <option key={cat.id_categoria} value={cat.id_categoria}>
                            {cat.nombre_categoria}
                        </option>
                    ))}
                </select>
            </div>
        </>
    )
}
