import { useEffect, useMemo, useState } from 'react'
import axios from 'axios'

function FakePostList() {
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [filterCategory, setFilterCategory] = useState('all')

  const loadProducts = async () => {
    setLoading(true)
    setError('')

    try {
      const response = await axios.get('https://fakestoreapi.com/products')
      setProducts(response.data || [])
    } catch (err) {
      setError('Unable to load fake API products')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadProducts()
  }, [])

  const categoryOptions = useMemo(
    () => [...new Set(products.map((product) => product.category))],[products],
  )

  const filteredProducts = useMemo(() => {
    if (filterCategory === 'all') {
      return products
    }
    return products.filter((product) => product.category === filterCategory)
  }, [products, filterCategory])

  return (
    <section>
      <div>
        <h2>Fetching from Fake API </h2>
        <button onClick={loadProducts} type="button">
          Refresh
        </button>
      </div>

      <div className="controls">
        <label>Filter by Category:</label>
        <select
          id="category-filter"
          value={filterCategory}
          onChange={(event) => setFilterCategory(event.target.value)}
        >
          <option value="all">All Categories</option>
          {categoryOptions.map((category) => (
            <option key={category} value={category}>
              {category}
            </option>
          ))}
        </select>
      </div>

      {loading && <p>Loading fake API products...</p>}
      {!loading && error && <p>{error}</p>}

      {!loading && !error && (
        <div>
          {filteredProducts.map((product) => (
            <article key={product.id} className="card-item">
              <h3>{product.title}</h3>
              <p>{product.description}</p>
              <p>
                <strong>Category:</strong> {product.category}
              </p>
              <p>
                <strong>Price:</strong> ${product.price}
              </p>
            </article>
          ))}
        </div>
      )}
    </section>
  )
}

export default FakePostList
