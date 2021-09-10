export function generateKey() {
  // TODO Consider replacing with uuid.v4()
  return String(window.crypto.getRandomValues(new Uint32Array(1))[0]);
}