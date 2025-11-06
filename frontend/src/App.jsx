import React, {useEffect, useState} from 'react';

const API = 'http://localhost:8080/api';

function App(){
  const [products, setProducts] = useState([]);
  const [cart, setCart] = useState([]);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [receipt, setReceipt] = useState(null);

  useEffect(()=>{ fetch(`${API}/products`).then(r=>r.json()).then(setProducts) },[]);

  const refreshCart = ()=> fetch(`${API}/cart`).then(r=>r.json()).then(setCart);

  useEffect(()=>{ refreshCart() },[]);

  const addToCart = (id)=> fetch(`${API}/cart`,{
    method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({productId:id, qty:1})
  }).then(()=>refreshCart());

  const removeItem = (id)=> fetch(`${API}/cart/`+id, {method:'DELETE'}).then(()=>refreshCart());

  const doCheckout = async ()=>{
    const res = await fetch(`${API}/cart/checkout`, {
      method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({name,email})
    });
    const json = await res.json();
    setReceipt(json);
  };

  const total = cart.reduce((s,it)=> s + it.product.price * it.qty, 0).toFixed(2);

  return (
    <div style={{maxWidth:1000, margin:'0 auto', padding:20, fontFamily:'Arial'}}>
      <h1>Vibe Commerce — Mock Cart</h1>
      <div style={{display:'flex', gap:20}}>
        <div style={{flex:1}}>
          <h2>Products</h2>
          <div style={{display:'grid', gridTemplateColumns:'repeat(2,1fr)', gap:12}}>
            {products.map(p=>(
              <div key={p.id} style={{border:'1px solid #ddd', padding:12, borderRadius:8}}>
                <h3>{p.name}</h3>
                <p>{p.description}</p>
                <div>₹{p.price}</div>
                <button onClick={()=>addToCart(p.id)} style={{marginTop:8}}>Add to cart</button>
              </div>
            ))}
          </div>
        </div>
        <div style={{width:320}}>
          <h2>Cart</h2>
          {cart.length===0 && <div>Cart is empty</div>}
          {cart.map(ci=>(
            <div key={ci.id} style={{borderBottom:'1px solid #eee', paddingBottom:8, marginBottom:8}}>
              <div><strong>{ci.product.name}</strong></div>
              <div>Qty: {ci.qty} • Price: ₹{ci.product.price}</div>
              <button onClick={()=>removeItem(ci.id)} style={{marginTop:6}}>Remove</button>
            </div>
          ))}
          <div style={{marginTop:12}}>
            <strong>Total: ₹{total}</strong>
          </div>
          <div style={{marginTop:12}}>
            <h3>Checkout</h3>
            <input placeholder="Name" value={name} onChange={e=>setName(e.target.value)} style={{width:'100%', marginBottom:6}}/>
            <input placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} style={{width:'100%', marginBottom:6}}/>
            <button onClick={doCheckout} disabled={!name||!email||cart.length===0}>Place order</button>
            {receipt && (
              <div style={{marginTop:12, border:'1px solid #ccc', padding:8}}>
                <h4>Receipt</h4>
                <div>Buyer: {receipt.buyerName} ({receipt.buyerEmail})</div>
                <div>Time: {new Date(receipt.timestamp).toLocaleString()}</div>
                <div>Total: ₹{receipt.total}</div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  )
}

export default App;
