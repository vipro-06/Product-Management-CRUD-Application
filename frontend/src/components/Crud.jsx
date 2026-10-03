import axios from "axios";
import { useEffect, useState } from "react";

// Base URL for the Product REST API (proxied to Spring Boot via Vite)
const API = "/api/products";

// Empty form values used for create mode and after reset
const emptyForm = { name: "", price: "", description: "" };

const Crud = () => {
  // All products loaded from the backend
  const [products, setProducts] = useState([]);

  // Controlled form fields (name, price, description)
  const [form, setForm] = useState(emptyForm);

  // When set, submit will update this product instead of creating a new one
  const [editId, setEditId] = useState(null);

  // GET /api/products — fetch all products and store them in state
  const loadProducts = async () => {
    const res = await axios.get(API);
    setProducts(res.data);
  };

  // Load products once when the component mounts
  useEffect(() => {
    loadProducts();
  }, []);

  // Keep form state in sync with input changes
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // POST create or PUT update, then refresh the list
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Payload expected by ProductController / Product entity
    const payload = {
      name: form.name,
      price: Number(form.price),
      description: form.description,
    };

    if (editId) {
      // PUT /api/products/{id}
      await axios.put(`${API}/${editId}`, payload);
      alert("Product updated successfully!");
    } else {
      // POST /api/products
      await axios.post(API, payload);
    }

    // Reset form back to create mode
    setForm(emptyForm);
    setEditId(null);
    loadProducts();
  };

  // Fill the form so the user can update an existing product
  const handleEdit = (product) => {
    setForm({
      name: product.name,
      price: String(product.price),
      description: product.description,
    });
    setEditId(product.id);
  };

  // DELETE /api/products/{id} — ask for confirmation, then remove and refresh
  const handleDelete = async (id) => {
    const ok = window.confirm("Are you sure you want to delete this product?");
    if (!ok) return;

    await axios.delete(`${API}/${id}`);

    // If we were editing the deleted product, clear the form
    if (editId === id) {
      setForm(emptyForm);
      setEditId(null);
    }

    loadProducts();
  };

  // Exit edit mode without saving
  const handleCancel = () => {
    setForm(emptyForm);
    setEditId(null);
  };

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-2xl font-semibold mb-6">Products</h1>

      {/* Two-column layout: form on the left, list on the right */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {/* Left — create / update form */}
        <form onSubmit={handleSubmit} className="space-y-3">
          <h2 className="text-lg font-medium">
            {editId ? "Update product" : "Add product"}
          </h2>

          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            placeholder="Name"
            required
            className="w-full border px-3 py-2 rounded"
          />

          <input
            name="price"
            type="number"
            step="0.01"
            value={form.price}
            onChange={handleChange}
            placeholder="Price"
            required
            className="w-full border px-3 py-2 rounded"
          />

          <input
            name="description"
            value={form.description}
            onChange={handleChange}
            placeholder="Description"
            className="w-full border px-3 py-2 rounded"
          />

          <div className="flex gap-2">
            <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded"
            >
              {editId ? "Update" : "Add"}
            </button>

            {editId && (
              <button
                type="button"
                onClick={handleCancel}
                className="border px-4 py-2 rounded"
              >
                Cancel
              </button>
            )}
          </div>
        </form>

        {/* Right — product list */}
        <div>
          <h2 className="text-lg font-medium mb-3">Product list</h2>

          <ul className="space-y-2">
            {products.length === 0 && (
              <li className="text-sm text-gray-500">No products yet.</li>
            )}

            {products.map((product) => (
              <li
                key={product.id}
                className="flex items-center justify-between border px-3 py-2 rounded"
              >
                <div>
                  <p className="font-medium">
                    {product.name} — ₹{product.price}
                  </p>
                  <p className="text-sm text-gray-600">{product.description}</p>
                </div>

                <div className="flex gap-2">
                  <button
                    type="button"
                    onClick={() => handleEdit(product)}
                    className="text-blue-600 text-sm"
                  >
                    Edit
                  </button>
                  <button
                    type="button"
                    onClick={() => handleDelete(product.id)}
                    className="text-red-600 text-sm"
                  >
                    Delete
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

 export default Crud;
