import React from 'react'
import './Banner.scss'

const slides = [
    {
      title: "Facing Doubts?",
      subtitle: "What questions will they ask me in interview?",
      description: "",
      image:
        "https://images.unsplash.com/photo-1516027828283-84217f09f3c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=428&q=80"
    },
    {
      title: "Don't Worry!! We got you covered!!",
      subtitle: "See the countless profiles and compare them with that of yourself",
      description: "",
      image:
        "https://images.unsplash.com/photo-1421986527537-888d998adb74?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"
    },
    {
      title: "Prepare!!!",
      subtitle: "Go through the precise and 100% real question asked in interviews and prepare",
      description: "",
      image:
        "https://images.unsplash.com/photo-1499750310107-5fef28a66643?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"
    },
    {
      title: "Connect!!",
      subtitle: "Connect with experts in the fields and learn..",
      description: "",
      image:
        "https://images.unsplash.com/photo-1580795479172-6c29db0fd7c4?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"
    },
    {
      title: "Give back!!!",
      subtitle: "Share your experience and help the community.",
      description: "",
      image:
        "https://images.unsplash.com/photo-1528605105345-5344ea20e269?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1170&q=80"
    }
  ];

  function useTilt(active) {
    const ref = React.useRef(null);
  
    React.useEffect(() => {
      if (!ref.current || !active) {
        return;
      }
  
      const state = {
        rect: undefined,
        mouseX: undefined,
        mouseY: undefined
      };
  
      let el = ref.current;
  
      const handleMouseMove = (e) => {
        if (!el) {
          return;
        }
        if (!state.rect) {
          state.rect = el.getBoundingClientRect();
        }
        state.mouseX = e.clientX;
        state.mouseY = e.clientY;
        const px = (state.mouseX - state.rect.left) / state.rect.width;
        const py = (state.mouseY - state.rect.top) / state.rect.height;
  
        el.style.setProperty("--px", px);
        el.style.setProperty("--py", py);
      };
  
      el.addEventListener("mousemove", handleMouseMove);
  
      return () => {
        el.removeEventListener("mousemove", handleMouseMove);
      };
    }, [active]);
  
    return ref;
  }
  
  const initialState = {
    slideIndex: 0
  };
  
  const slidesReducer = (state, event) => {
    if (event.type === "NEXT") {
      return {
        ...state,
        slideIndex: (state.slideIndex + 1) % slides.length
      };
    }
    if (event.type === "PREV") {
      return {
        ...state,
        slideIndex:
          state.slideIndex === 0 ? slides.length - 1 : state.slideIndex - 1
      };
    }
  };

  function Slide({ slide, offset }) {


    const active = offset === 0 ? true : null;
    const ref = useTilt(active);



    return (
        <div
        ref={ref}
        className="slide"
        data-active={active}
        style={{
          "--offset": offset,
          "--dir": offset === 0 ? 0 : offset > 0 ? 1 : -1
        }}
      >
        <div
          className="slideBackground"
          style={{
            backgroundImage: `url('${slide.image}')`
          }}
        />
        <div
          className="slideContent"
          style={{
            backgroundImage: `url('${slide.image}')`
          }}
        >
          <div className="slideContentInner">
            <h2 className="slideTitle">{slide.title}</h2>
            <h3 className="slideSubtitle">{slide.subtitle}</h3>
            <p className="slideDescription">{slide.description}</p>
          </div>
        </div>
      </div>
    )
}

function Banner() {

    const [state, dispatch] = React.useReducer(slidesReducer, initialState);


    
    return (
        <div className='banner-1'>
            <div className='banner-2'>
                <div className='banner-3'>
        <div className="slides">
            <button onClick={() => dispatch({ type: "PREV" })}>‹</button>
  
  {[...slides, ...slides, ...slides].map((slide, i) => {
    let offset = slides.length + (state.slideIndex - i);
    return <Slide slide={slide} offset={offset} key={i} />;
  })}
  <button onClick={() => dispatch({ type: "NEXT" })}>›</button>
        </div>
        </div>
        </div>
        </div>
    )
}

export default Banner


