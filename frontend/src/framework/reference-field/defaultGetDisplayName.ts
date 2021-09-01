export function defaultGetDisplayName(value: any) {
  if ('name' in value) {
    return value.name;
  }
  if ('id' in value) {
    return value.id;
  }
  return JSON.stringify(value);
}