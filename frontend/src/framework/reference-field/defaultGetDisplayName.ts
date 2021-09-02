export function defaultGetDisplayName(value: any) {
  if ('name' in value) {
    return value.name;
  }
  if ('caption' in value) {
    return value.caption;
  }
  if ('label' in value) {
    return value.label;
  }
  if ('id' in value) {
    return value.id;
  }
  return JSON.stringify(value);
}